/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.restful;

import java.util.ArrayList;
import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.phoenix.programschedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programschedule.service.ProgramScheduleService;
import sg.edu.nus.iss.phoenix.radioprogram.entity.RadioProgram;
import sg.edu.nus.iss.phoenix.radioprogram.restful.RadioPrograms;

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
    
    @PUT
    @Path("/create_annualschedule")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAnnualSchedule(AnnualSchedule annualSchedule) {
        programScheduleService.processCreate(annualSchedule);
    }
}
