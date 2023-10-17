/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.anvu.datetimechecker.resource;

import com.anvu.datetimechecker.dto.response.ErrBox;
import com.anvu.datetimechecker.dto.response.ErrDTO;
import com.anvu.datetimechecker.dto.response.ErrorCode;
import com.anvu.datetimechecker.dto.MonthYearDTO;
import com.anvu.datetimechecker.dto.MyDateDTO;
import com.anvu.datetimechecker.dto.response.ResDTO;
import com.anvu.datetimechecker.exception.MyDateTimeException;
import com.anvu.datetimechecker.utils.Utilities;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.everit.json.schema.Schema;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONException;

/**
 *
 * @author hoang
 */
@Path("main")
public class DateTimeChecker {
    
    private static final String ERROR_MESSAGE = "Request failed";
    private static final String SUCCESS_MESSAGE = "Request successfully";
    private Utilities utilsInstance = Utilities.getInstance();

    @POST
    @Path("get-max-day")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkDayInMonth(String req) throws MyDateTimeException {
        ErrBox<Map<String, Object>> errBox = validateJSON(req, MonthYearDTO.getJSONSchema());
        if (!errBox.getDescription().isEmpty()) {
            ErrDTO<Map<String, Object>> err = new ErrDTO(errBox.getDescription(), errBox.getCodes(), Response.Status.BAD_REQUEST, ERROR_MESSAGE);
            throw new MyDateTimeException(err, Response.Status.BAD_REQUEST);
        }
        JSONObject json = new JSONObject(req);
        int year = json.getInt("year");
        int month = json.getInt("month");
        int day = utilsInstance.calculateDayInMonth(year, month);
        Map<String, Integer> res = new HashMap();
        res.put("day", day);
        return Response.ok().entity(new ResDTO<>(res, Response.Status.OK, SUCCESS_MESSAGE)).build();
    }
    
    @POST
    @Path("check-date")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response checkDate(String req) throws MyDateTimeException {
        ErrBox<Map<String, Object>> errBox = validateJSON(req, MyDateDTO.getJSONSchema());
        if (errBox.getDescription().containsKey("format")) {
            throw new MyDateTimeException(new ErrDTO(errBox.getDescription(), errBox.getCodes(), Response.Status.BAD_REQUEST, ERROR_MESSAGE), Response.Status.BAD_REQUEST);
        }
        JSONObject json = new JSONObject(req);
        int day = 0, month = 0, year = 0;
        boolean checkErrBoxContainKeyDay = false;
        if (!errBox.getDescription().containsKey("day")) {
            day = json.getInt("day");
            if(!errBox.getDescription().containsKey("month")) {
                month = json.getInt("month");
                if (!errBox.getDescription().containsKey("year")) {
                    year = json.getInt("year");
                    if (!utilsInstance.isValidDate(year, month, day)) {
                        int maxDay = utilsInstance.calculateDayInMonth(year, month);
                        if (month == 2 && maxDay == 28) {
                            errBox.getDescription().put("day", "day must be from 1 to " + maxDay + " (normal year)");
                        } else if (month == 2 && maxDay == 29) {
                            errBox.getDescription().put("day", "day must be from 1 to " + maxDay + " (leap year)");
                        } else {
                            errBox.getDescription().put("day", "day must be from 1 to " + maxDay);
                        }
                    }
                } else {
                    switch (month) {
                        case 1: case 3: case 5: case 7: case 8:case 10: case 12: {
                            if (day < 1 || day > 31) {
                                errBox.getDescription().put("day", "day must be from 1 to 31");
                            }
                            break;
                        }
                        case 4: case 6: case 9: case 11: {
                            if (day < 1 || day > 30) {
                                errBox.getDescription().put("day", "day must be from 1 to 30");
                            }
                            break;
                        }
                        case 2: {
                            if (day < 1 || day > 29) {
                                errBox.getDescription().put("day", "day must be from 1 to 28 for a normal year or from 1 to 29 for a leap year, server is unable to determine year");
                            }
                            break;
                        }
                    }
                }
            } else {
                if (day < 1 || day > 31) {
                    errBox.getDescription().put("day", "day is out of range [1, 31], server is unable to determine month");
                }
            }
        } else {
            checkErrBoxContainKeyDay = true;
        }
        if (!errBox.getDescription().isEmpty()) {
            if (errBox.getDescription().containsKey("day") && !errBox.getCodes().contains(ErrorCode.INVALID_DATA) && !checkErrBoxContainKeyDay) {
                errBox.getCodes().add(ErrorCode.INVALID_DATA);
            }
            throw new MyDateTimeException(new ErrDTO(errBox.getDescription(), errBox.getCodes(), Response.Status.BAD_REQUEST, ERROR_MESSAGE), Response.Status.BAD_REQUEST);
        }
        Map<String, String> resList = new HashMap();
        resList.put("message", "Existence date (" + day + "/" + month + "/" + year + ")");
        ResDTO<Map<String, String>> res = new ResDTO(resList, Response.Status.OK, SUCCESS_MESSAGE);
        return Response.ok().entity(res).build();
    }
    
    private ErrBox<Map<String, Object>> validateJSON(String jsonString, String schemaJSON) {
        Map<String, Object> errList = new HashMap();
        ArrayList<ErrorCode> codeList = new ArrayList();
        try {
            Schema schema = SchemaLoader.load(new JSONObject(schemaJSON));
            JSONObject json = new JSONObject(jsonString);
            schema.validate(json);
        } catch (ValidationException e) {
            List<String> ErrKeyVals = e.getAllMessages();
            for (String keyValue : ErrKeyVals) {
                int firstColon = keyValue.indexOf(":");
                String key = keyValue.substring(0, firstColon).trim().replaceAll("^#/|^#", "");
                String value = keyValue.substring(firstColon + 1, keyValue.length()).trim();
                int countUnknownErr = 0;
                if (key.isEmpty()) {
                    if (value.contains("permit")) {
                        key = "noPermission";
                        value = "extraneous keys are not permitted";
                        if (!codeList.contains(ErrorCode.UNPERMITTED)) {
                            codeList.add(ErrorCode.UNPERMITTED);
                        }
                    }else if (value.contains("required")) {
                        key = value.substring(value.indexOf("[") + 1, value.indexOf("]"));
                        if (!codeList.contains(ErrorCode.REQUIRED)) {
                            codeList.add(ErrorCode.REQUIRED);
                        }
                    } else {
                        key = "unknownErr" + countUnknownErr++;
                        if (!codeList.contains(ErrorCode.UNKNOWN_ERROR)) {
                            codeList.add(ErrorCode.UNKNOWN_ERROR);
                        }
                    }
                } else {
                    if (!codeList.contains(ErrorCode.INVALID_DATA)) {
                        codeList.add(ErrorCode.INVALID_DATA);
                    }
                }
                errList.put(key, value);
            }
        }catch (JSONException e) {
            errList.put("JSONFormatRule", "invalid format JSON object rule");
            codeList.add(ErrorCode.INVALID_JSON_RULE);
        } catch (Exception e) {
            errList.put("unIdentifierError", "an unknown error occurs");
            codeList.add(ErrorCode.UNKNOWN_ERROR);
        }
        return new ErrBox(errList, codeList);
    }
}
