package com.paywallet.POJOClasses;

import java.util.List;

public class CCUpdate {

    List<String> profiles;
    Updateallocation updateAllocation;

    public  CCUpdate(List<String>  profiles, Updateallocation updateallocation)
    {
        this.profiles = profiles;
        this.updateAllocation = updateallocation;
    }

    public List<String> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<String> profiles) {
        this.profiles = profiles;
    }

    public Updateallocation getUpdateAllocation() {
        return updateAllocation;
    }

    public void setUpdateAllocation(Updateallocation updateAllocation) {
        this.updateAllocation = updateAllocation;
    }
}
