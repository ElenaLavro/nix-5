package com.company.program;

import com.company.annotation.PropertyKey;

public class AppProperties {
    @PropertyKey("OS")
    public String operatingSystem;

    @PropertyKey("CPU")
    public String centralProcessor;

    @PropertyKey("RAM")
    public String memory;

    @PropertyKey("Space")
    public String space;

    @PropertyKey("Videoadapter")
    public String adapter;
}
