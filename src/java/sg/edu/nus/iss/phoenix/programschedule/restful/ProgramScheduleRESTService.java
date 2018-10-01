/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.restful;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Date;
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
 *REST Web Service/ end point for program schedule
 * @author Ragu
 */
@Path("programschedule")
@RequestScoped
public class ProgramScheduleRESTService {
    private ProgramScheduleService programScheduleService;
    
     /**
     * Creates a new instance of ProgramScheduleRESTService
     */
    public ProgramScheduleRESTService() {
        programScheduleService = new ProgramScheduleService();
    }
    
    /**
     * Gets all annual schedules
     * @return List of annual schedule objects
     */
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
    
    /**
     * Gets all weekly schedules of a requested year
     * @param year String representing year
     * @return List of weekly schedules
     */
    @GET
    @Path("/all_weeklySchedule/{year}")
    @Produces(MediaType.APPLICATION_JSON)
    public WeeklySchedules getAllWeeklySchedules(@PathParam("year") String year) {
        int annualYear = Integer.parseInt(year);
        ArrayList<WeeklySchedule> wslist = programScheduleService.findWSByYear(annualYear);
        WeeklySchedules WSsList = new WeeklySchedules();
        WSsList.setWeeklySchedules(new ArrayList<WeeklySchedule>());
        
        for (int i = 0; i < wslist.size(); i++) {
            WSsList.getWeeklySchedules().add(
                new WeeklySchedule(wslist.get(i).getStartDate(), 
                    wslist.get(i).getAssignedBy(), wslist.get(i).getYear()));
        }

        return WSsList;
    }
    
    /**
     * Creates an annual schedule with the passed parameter
     * @param annualSchedule Annual schedule object to be created in database
     */
    @PUT
    @Path("/create_annualschedule")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAnnualSchedule(AnnualSchedule annualSchedule) {
        programScheduleService.processCreate(annualSchedule);
    }
    
    /**
     * Creates a weekly schedule with the passed parameter
     * @param weeklySchedule Weekly schedule object to be created in database
     */
    @PUT
    @Path("/create_weeklyschedule")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createWeeklySchedule(WeeklySchedule weeklySchedule) {
        System.out.println(" Rest createWeeklySchedule :" + weeklySchedule.getAssignedBy() + " " + weeklySchedule.getStartDate());
        programScheduleService.processCreateWeek(weeklySchedule);
    }
    
    /**
     * Returns all program slots 
     * @return Lit of program slots
     */
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
                    pslist.get(i).getstartTime(),
                pslist.get(i).getweekStartDate(),
                pslist.get(i).getpresenter(),
                pslist.get(i).getproducer()));
        }

        return slotList;
    }
    
    /**
     * Returns a list of program slots by request week start date
     * @param weekStartDate week start date
     * @return List of program slots 
     */
      @GET
    @Path("/all_programslots/{ws_startDate}")
    @Produces(MediaType.APPLICATION_JSON)
    public ProgramSlotList getProgramSlotbyWeek(@PathParam("ws_startDate") Date weekStartDate) {
        ArrayList<ProgramSlot> pslist = programScheduleService.findProgramSlot(weekStartDate);
        ProgramSlotList slotList = new ProgramSlotList();
        slotList.setSlotList(new ArrayList<ProgramSlot>());
        
        for (int i = 0; i < pslist.size(); i++) {
            slotList.getSlotList().add(
                new ProgramSlot(pslist.get(i).getprogramSlotName(), 
                    pslist.get(i).getdateofProgram(), pslist.get(i).getduration(),pslist.get(i).getstartTime(),
                pslist.get(i).getweekStartDate(),pslist.get(i).getpresenter(),pslist.get(i).getproducer()));
        }

        return slotList;
    }
    
    
 
    /**
     * POST method for updating or creating an instance of resource
     * @param ps The updated program slot object 
     */
    @POST
    @Path("/updateProgramSlot")
    @Consumes(MediaType.APPLICATION_JSON)
    public void updateProgramSlot(ProgramSlot ps) {
        programScheduleService.processModifyProgramSlot(ps);
    }
    
    /**
     * PUT method for creating an instance of resource
     * @param ps The updated program slot object
     */
    @PUT
    @Path("/createProgramSlot")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createProgramSlot(ProgramSlot ps) {
        programScheduleService.processCreateProgramSlot(ps);
    }
   
    /**
     * DELETE method for deleting an instance of resource
     * @param name name of the program slot 
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
