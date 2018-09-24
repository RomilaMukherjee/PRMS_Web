/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.restful;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import sg.edu.nus.iss.phoenix.programschedule.entity.AnnualSchedule;
import sg.edu.nus.iss.phoenix.programschedule.service.ProgramScheduleService;

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
    
    @PUT
    @Path("/create_annualschedule")
    @Consumes(MediaType.APPLICATION_JSON)
    public void createAnnualSchedule(AnnualSchedule annualSchedule) {
        programScheduleService.processCreate(annualSchedule);
    }
}
