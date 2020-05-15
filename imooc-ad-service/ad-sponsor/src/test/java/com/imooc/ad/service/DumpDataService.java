package com.imooc.ad.service;

import com.alibaba.fastjson.JSON;
import com.imooc.ad.Application;
import com.imooc.ad.constant.CommonStatus;
import com.imooc.ad.dao.AdPlanRepository;
import com.imooc.ad.dao.AdUnitRepository;
import com.imooc.ad.dao.CreativeRepository;
import com.imooc.ad.dao.unit_condition.AdUnitDistrictRepository;
import com.imooc.ad.dao.unit_condition.AdUnitItRepository;
import com.imooc.ad.dao.unit_condition.AdUnitKeywordRepository;
import com.imooc.ad.dao.unit_condition.CreativeUnitRepository;
import com.imooc.ad.dump.DConstant;
import com.imooc.ad.dump.table.*;
import com.imooc.ad.entity.AdPlan;
import com.imooc.ad.entity.AdUnit;
import com.imooc.ad.entity.Creative;
import com.imooc.ad.entity.unit_condition.AdUnitDistrict;
import com.imooc.ad.entity.unit_condition.AdUnitIt;
import com.imooc.ad.entity.unit_condition.AdUnitKeyword;
import com.imooc.ad.entity.unit_condition.CreativeUnit;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {Application.class},
            webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class DumpDataService {
    @Autowired
    private AdPlanRepository planRepository;
    @Autowired
    private AdUnitRepository unitRepository;
    @Autowired
    private CreativeRepository creativeRepository;
    @Autowired
    private CreativeUnitRepository creativeUnitRepository;
    @Autowired
    private AdUnitDistrictRepository districtRepository;
    @Autowired
    private AdUnitItRepository itRepository;
    @Autowired
    private AdUnitKeywordRepository keywordRepository;

    @Test
    public void dumpAdTableData(){
        dumpAdPlanTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_PLAN)
        );
        dumpAdUnitTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT)
        );
        dumpAdCreativeTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_CREATIVE)
        );
        dumpAdCreativeUnitTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_CREATIVE_UNIT)
        );
        dumpAdUnitDistrict(
                String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT_DISTRICT)
        );
        dumpAdItTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT_IT)
        );
        dumpAdUnitKeywordTable(
                String.format("%s%s", DConstant.DATA_ROOT_DIR, DConstant.AD_UNIT_KEYWORD)
        );
    }

    private void dumpAdPlanTable(String fileName) {
        List<AdPlan> adPlans = planRepository.findAllByPlanStatus(
                CommonStatus.VALID.getStatus()
        );
        if(CollectionUtils.isEmpty(adPlans)){
            return;
        }
        List<AdPlanTable> planTables = new ArrayList<>();
        adPlans.forEach(p -> planTables.add(
                new AdPlanTable(
                        p.getId(),
                        p.getUserId(),
                        p.getPlanStatus(),
                        p.getStartDate(),
                        p.getEndDate()
                )
        ));
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for(AdPlanTable adPlanTable:planTables){
                writer.write(JSON.toJSONString(adPlanTable));
                writer.newLine();
            }
        }catch(IOException ex) {
            log.error("dump AdPlanTable error");
        }
    }
    private void dumpAdUnitTable(String fileName){
        List<AdUnit> adUnits = unitRepository.findAllByUnitStatus(
                CommonStatus.VALID.getStatus()
        );
        if(CollectionUtils.isEmpty(adUnits)){
            return;
        }
        List<AdUnitTable> unitTables = new ArrayList<>();
        adUnits.forEach(u -> unitTables.add(
                new AdUnitTable(
                        u.getId(),
                        u.getUnitStatus(),
                        u.getPositionType(),
                        u.getPlanId()
                )
        ));
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for(AdUnitTable unitTable:unitTables){
                writer.write(JSON.toJSONString(unitTable));
                writer.newLine();
            }
        }catch(IOException ex) {
            log.error("dump AdUnitTable error");
        }

    }

    private void dumpAdCreativeTable(String fileName){
        List<Creative> creatives = creativeRepository.findAll();
        if(CollectionUtils.isEmpty(creatives)) return;
        List<AdCreativeTable> creativeTables = new ArrayList<>();
        creatives.forEach(c->creativeTables.add(
                new AdCreativeTable(
                        c.getId(),
                        c.getName(),
                        c.getType(),
                        c.getMaterialType(),
                        c.getHeight(),
                        c.getWidth(),
                        c.getAuditStatus(),
                        c.getUrl()
                )
        ));
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for(AdCreativeTable creativeTable:creativeTables){
                writer.write(JSON.toJSONString(creativeTable));
                writer.newLine();
            }
        }catch(IOException ex) {
            log.error("dump AdCreativeTable error");
        }
    }

    private void dumpAdCreativeUnitTable(String fileName){
        List<CreativeUnit> creativeUnits = creativeUnitRepository.findAll();
        if(CollectionUtils.isEmpty(creativeUnits)){
            return;
        }

        List<AdCreativeUnitTable> creativeUnitTables = new ArrayList<>();
        creativeUnits.forEach(c -> creativeUnitTables.add(
                new AdCreativeUnitTable(
                        c.getCreativeId(),
                        c.getUnitId()
                )
        ));
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for(AdCreativeUnitTable creativeUnitTable:creativeUnitTables){
                writer.write(JSON.toJSONString(creativeUnitTable));
                writer.newLine();
            }
        }catch(IOException ex) {
            log.error("dump AdCreativeUnitTable error");
        }
    }

    private void dumpAdUnitDistrict(String fileName){
        List<AdUnitDistrict> unitDistricts = districtRepository.findAll();
        if(CollectionUtils.isEmpty(unitDistricts)){
            return;
        }

        List<AdUnitDistrictTable> unitDistrictTables = new ArrayList<>();
        unitDistricts.forEach(u -> unitDistrictTables.add(
                new AdUnitDistrictTable(
                        u.getUnitId(),
                        u.getProvince(),
                        u.getCity()
                )
        ));
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for(AdUnitDistrictTable adUnitDistrictTable:unitDistrictTables){
                writer.write(JSON.toJSONString(adUnitDistrictTable));
                writer.newLine();
            }
        }catch(IOException ex) {
            log.error("dump AdUnitDistrictTable error");
        }
    }

    private void dumpAdItTable(String fileName){
        List<AdUnitIt> adUnitIts = itRepository.findAll();
        if(CollectionUtils.isEmpty(adUnitIts)){
            return;
        }

        List<AdUnitItTable> adUnitItTables = new ArrayList<>();
        adUnitIts.forEach(a -> adUnitItTables.add(
                new AdUnitItTable(
                        a.getUnitId(),
                        a.getItTag()
                )
        ));
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for(AdUnitItTable unitItTable:adUnitItTables){
                writer.write(JSON.toJSONString(unitItTable));
                writer.newLine();
            }
        }catch(IOException ex) {
            log.error("dump AdUnitItTable error");
        }
    }

    private void dumpAdUnitKeywordTable(String fileName){
        List<AdUnitKeyword> adUnitKeywords = keywordRepository.findAll();
        if(CollectionUtils.isEmpty(adUnitKeywords)) return;

        List<AdUnitKeywordTable> unitKeywordTables = new ArrayList<>();
        adUnitKeywords.forEach(a -> unitKeywordTables.add(
                new AdUnitKeywordTable(
                        a.getUnitId(),
                        a.getKeyword()
                )
        ));
        Path path = Paths.get(fileName);
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            for(AdUnitKeywordTable keywordTable:unitKeywordTables){
                writer.write(JSON.toJSONString(keywordTable));
                writer.newLine();
            }
        }catch(IOException ex) {
            log.error("dump AdUnitKeywordTable error");
        }
    }

}
