package com.example.demo.service.impl;

import com.example.demo.dict.DictItem;
import com.example.demo.service.IDictService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DictServiceImpl implements IDictService {

    @Override
    public List<DictItem> init() {
        List<DictItem> dicts = new ArrayList<>();
        dicts.add(new DictItem().setDictType("SEX").setName("女").setValue("0"));
        dicts.add(new DictItem().setDictType("SEX").setName("男").setValue("0"));
        dicts.add(new DictItem().setDictType("STUDENT_STATUS").setName("禁用").setValue("0"));
        dicts.add(new DictItem().setDictType("STUDENT_STATUS").setName("启用").setValue("1"));
        return dicts;
    }
}


