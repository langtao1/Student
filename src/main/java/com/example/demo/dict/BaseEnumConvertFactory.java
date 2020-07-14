package com.example.demo.dict;

import com.example.demo.enums.BaseEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @Description:
 * @Author: eagle
 * @Date: 2020-07-12 0:15
 */
@Component
public class  BaseEnumConvertFactory implements ConverterFactory<String, BaseEnum> {
    @Override
    public <T extends BaseEnum> Converter<String, T> getConverter(Class<T> targetType) {
        return new StringToIEum(targetType);
    }

    @SuppressWarnings("all")
    private static class StringToIEum<T extends BaseEnum> implements Converter<String, T> {
        private Class<T> targerType;

        public StringToIEum(Class<T> targerType) {
            this.targerType = targerType;
        }
        @Override
        public T convert(String source) {
            if (StringUtils.isEmpty(source)) {
                return null;
            }
            return (T) BaseEnumConvertFactory.getIEnum(this.targerType, source);
        }
    }

    public static <T extends BaseEnum> Object getIEnum(Class<T> targerType, String source) {
        for (T enumObj : targerType.getEnumConstants()) {
            if (source.equals(String.valueOf(enumObj.getValue()))) {
                return enumObj;
            }
        }
        return null;
    }
}

