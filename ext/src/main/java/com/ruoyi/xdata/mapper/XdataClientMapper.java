package com.ruoyi.xdata.mapper;

import java.util.List;
import com.ruoyi.xdata.domain.XdataClient;

/**
 * 应用Mapper接口
 * 
 * @author yepanpan
 * @date 2020-12-29
 */
public interface XdataClientMapper 
{
    /**
     * 查询应用
     * 
     * @param id 应用ID
     * @return 应用
     */
    public XdataClient selectXdataClientById(Long id);

    /**
     * 查询应用列表
     * 
     * @param xdataClient 应用
     * @return 应用集合
     */
    public List<XdataClient> selectXdataClientList(XdataClient xdataClient);

    /**
     * 新增应用
     * 
     * @param xdataClient 应用
     * @return 结果
     */
    public int insertXdataClient(XdataClient xdataClient);

    /**
     * 修改应用
     * 
     * @param xdataClient 应用
     * @return 结果
     */
    public int updateXdataClient(XdataClient xdataClient);

    /**
     * 删除应用
     * 
     * @param id 应用ID
     * @return 结果
     */
    public int deleteXdataClientById(Long id);

    /**
     * 批量删除应用
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteXdataClientByIds(Long[] ids);
}
