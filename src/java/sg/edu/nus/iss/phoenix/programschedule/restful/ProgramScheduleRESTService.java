/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.restful;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.phoenix.programschedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programschedule.entity.WeeklySchedule;
import sg.edu.nus.iss.phoenix.programschedule.service.ProgramScheduleService;
import sg.edu.nus.iss.phoenix.programschedule.entity.ProgramSlot;
import sg.edu.nus.iss.phoenix.programschedule.restful.ProgramSlotList;

/**
 *
 * @author Ragu
 */
@Path("programschedule")
@RequestScoped
public class ProgramScheduleRESTService {
    private ProgramScheduleService programScheduleService;
    
    public ProgramScheduleRESTService() {
        programScheduleService = new ProgramScheduleService();
    }
    
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public AnnualSchedules getAllAnnualSchedules() {
        ArrayList<AnnualSchedule> aslist = programScheduleService.findAllAS();
        AnnualSchedules ASsList = new AnnualSchedules();
        ASsList.setASList(new ArrayList<AnnualSchedule>());
        
        for (int i = 0; i < aslist.size(); i++) {
            ASsList.getASList().add(
                new AnnualSchedule(aslist.get(i).getYear(), 
                    aslist.get(i).getAssignedBy()));
        }

        return ASsList;
    }
    
    @GET
    @Path("/all_weeklySchedule")
    @Produces(MediaType.APPLICATION_JSON)
    public WeeklySchedules getAllWeeklySchedules() {
        ArrayList<WeeklySchedule> wslist = programScheduleService.findAllWS();
        WeeklySchedules WSsList = new WeeklySchedules();
        WSsList.setWeeklySchedules(new ArrayList<WeeklySchedule>());
        
        for (int i = 0; i < wslist.size(); i++) {
            WSsList.getWeeklySchedules().add(
                new WeeklySchedule(wslist.get(i).getStartDate(), 
                    wslist.get(i).getAssignedBy()));
        }

        return WSsList;
    }
    
    @PUT
    @Path("/create_annualschedule")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAnnualSchedule(AnnualSchedule annualSchedule) {
        programScheduleService.processCreate(annualSchedule);
    }
    
    @PUT
    @Path("/create_weeklyschedule")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createWeeklySchedule(WeeklySchedule weeklySchedule) {
        System.out.println(" Rest createWeeklySchedule :" + weeklySchedule.getAssignedBy() + " " + weeklySchedule.getStartDate());
        programScheduleService.processCreateWeek(weeklySchedule);
    }
    
        @GET
    @Path("/all_programslots")
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramSlotList getAllProgramSlots() {
        ArrayList<ProgramSlot> pslist = programScheduleService.findAllProgramSlot();
        ProgramSlotList slotList = new ProgramSlotList();
        slotList.setSlotList(new ArrayList<ProgramSlot>());
        
        for (int i = 0; i < pslist.size(); i++) {
            slotList.getSlotList().add(
                new ProgramSlot(pslist.get(i).getprogramSlotName(), 
                    pslist.get(i).getdateofProgram(),
                    pslist.get(i).getduration(),
                    pslist.get(i).getstartTime()));
        }

        return slotList;
    }
    
    /**
     * PUT method for updating or creating an instance of resource
     * @param content representation for the resource
     */
    @POST
    @Path("/updateProgramSlot")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProgramSlot(ProgramSlot ps) {
        programScheduleService.processModifyProgramSlot(ps);
    }
    
    /**
     * POST method for creating an instance of resource
     * @param content representation for the resource
     */
    @PUT
    @Path("/createProgramSlot")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createProgramSlot(ProgramSlot ps) {
        programScheduleService.processCreateProgramSlot(ps);
    }
   
    /**
     * DELETE method for deleting an instance of resource
     * @param name name of the resource
     */
    @DELETE
    @Path("/delete/{psname}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void deleteProgramSlot(@PathParam("psname") String name) {
        String slotname;
        try { 
            slotname = URLDecoder.decode(name, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace(); 
            return;
        }

        programScheduleService.processDeleteProgramSlot(slotname);
    }
}
