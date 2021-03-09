package com.ruoyi.xdata.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.xdata.mapper.XdataClientMapper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sign.Md5Utils;
import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.xdata.domain.XdataClient;
import com.ruoyi.xdata.service.IXdataClientService;

/**
 * 应用Service业务层处理
 * 
 * @author yepanpan
 * @date 2020-12-29
 */
@Service
public class XdataClientServiceImpl implements IXdataClientService 
{
    @Autowired
    private XdataClientMapper xdataClientMapper;

    /**
     * 查询应用
     * 
     * @param id 应用ID
     * @return 应用
     */
    @Override
    public XdataClient selectXdataClientById(Long id)
    {
        return xdataClientMapper.selectXdataClientById(id);
    }

    /**
     * 查询应用列表
     * 
     * @param xdataClient 应用
     * @return 应用
     */
    @Override
    public List<XdataClient> selectXdataClientList(XdataClient xdataClient)
    {
        return xdataClientMapper.selectXdataClientList(xdataClient);
    }

    /**
     * 新增应用
     * 
     * @param xdataClient 应用
     * @return 结果
     */
    @Override
    public int insertXdataClient(XdataClient xdataClient)
    {
    	if(StringUtils.isBlank(xdataClient.getCode())) {
    		xdataClient.setCode(Md5Utils.hash(UUID.fastUUID().toString()));
    	}
    	if(StringUtils.isBlank(xdataClient.getSecret())) {
    		xdataClient.setSecret(Md5Utils.hash(UUID.fastUUID().toString()));
    	}
        return xdataClientMapper.insertXdataClient(xdataClient);
    }

    /**
     * 修改应用
     * 
     * @param xdataClient 应用
     * @return 结果
     */
    @Override
    public int updateXdataClient(XdataClient xdataClient)
    {
        return xdataClientMapper.updateXdataClient(xdataClient);
    }

    /**
     * 批量删除应用
     * 
     * @param ids 需要删除的应用ID
     * @return 结果
     */
    @Override
    public int deleteXdataClientByIds(Long[] ids)
    {
        return xdataClientMapper.deleteXdataClientByIds(ids);
    }

    /**
     * 删除应用信息
     * 
     * @param id 应用ID
     * @return 结果
     */
    @Override
    public int deleteXdataClientById(Long id)
    {
        return xdataClientMapper.deleteXdataClientById(id);
    }
}
