package com.example.mapper;

import com.example.entity.*;
import com.example.repository.*;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RecordMapper{
    @Autowired
    private RecordRepository recordRepository;
    
    public Record createRecord(Record rec){
        recordRepository.saveAndFlush(rec);
        return rec;
    }
}