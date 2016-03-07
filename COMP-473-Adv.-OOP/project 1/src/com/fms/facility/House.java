package com.fms.facility;

import com.fms.facilityrequest.IRequest;
import com.fms.facilityschedule.ISchedule;
import com.fms.facilityuse.IUsage;

import java.util.List;

public class House extends Unit {

    private String houseSpecificField;

    public House(String houseSpecificField){
        super();
        this.houseSpecificField = houseSpecificField;
    }
    public String getHouseSpecificField(){
        return houseSpecificField;
    }

    public void setHouseSpecificField(String houseSpecificField){
        this.houseSpecificField= houseSpecificField;
    }

}
