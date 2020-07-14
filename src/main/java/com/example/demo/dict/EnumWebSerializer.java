package com.example.demo.dict;

import com.example.demo.enums.BaseEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @Description:
 * @Author: eagle
 * @Date: 2020-07-11 23:20
 */
public class EnumWebSerializer extends JsonSerializer<BaseEnum> {
    @Override
    public void serialize(BaseEnum value, JsonGenerator jgen, SerializerProvider provider)
            throws IOException {
        if(value==null){
            jgen.writeNull();

        }
        jgen.writeObject(value.getValue());
        jgen.writeFieldName(jgen.getOutputContext().getCurrentName()+"Name");
        jgen.writeString(getEnumDesc(value));
    }

    @Override
    public Class handledType() {
        return BaseEnum.class;
    }

    private  String getEnumDesc(BaseEnum baseEnum) {
        //此处从缓存中读取字典的信息
        return DictManger.getDictName(baseEnum.getCode(),baseEnum.getValue());
    }
}