package com.natswell.sample.ddd.common.media;

import com.google.gson.JsonObject;

public class RepresentationReader extends AbstractJSONMediaReader {

    public RepresentationReader(String aJSONRepresentation) {
        super(aJSONRepresentation);
    }
    
    public RepresentationReader(JsonObject aRepresentationObject) {
        super(aRepresentationObject);
    }
}
