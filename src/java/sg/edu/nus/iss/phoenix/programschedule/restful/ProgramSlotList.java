/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.edu.nus.iss.phoenix.programschedule.restful;

import java.util.List;
import sg.edu.nus.iss.phoenix.programschedule.entity.ProgramSlot;

/**
 * Class representing a list of program slots
 * @author dharini
 */
public class ProgramSlotList {
    /**
     * List of program slots
     */    
    private List <ProgramSlot> slotList;

    
    public List<ProgramSlot> getSlotList() {
        return slotList;
    }
 
    public void setSlotList(List<ProgramSlot> slotList) {
        this.slotList = slotList;
    }
}
