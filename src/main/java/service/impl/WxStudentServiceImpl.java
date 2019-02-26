package service.impl;

import dao.mapper.WxStudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.WxStudent;
import pojo.WxStudentExample;
import service.WxStudentService;

@Service
public class WxStudentServiceImpl implements WxStudentService {

    @Autowired
    WxStudentMapper wxStudentMapper;

    public WxStudent selectByOpenId(String openId){
        WxStudentExample example=new WxStudentExample();
        WxStudentExample.Criteria criteria = example.createCriteria();
        criteria.andOpenidEqualTo(openId);
        return wxStudentMapper.selectByExample(example).size()>0?wxStudentMapper.selectByExample(example).get(0):null;
    }

}
