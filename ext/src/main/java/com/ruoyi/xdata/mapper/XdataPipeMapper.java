package com.ruoyi.xdata.mapper;

import java.util.List;
import com.ruoyi.xdata.domain.XdataPipe;

/**
 * 通道Mapper接口
 * 
 * @author yepanpan
 * @date 2020-12-29
 */
public interface XdataPipeMapper 
{
    /**
     * 查询通道
     * 
     * @param id 通道ID
     * @return 通道
     */
    public XdataPipe selectXdataPipeById(Long id);

    /**
     * 查询通道
     * 
     * @return 通道集合
     */
    public List<XdataPipe> selectJobs();
    
    
    /**
     * 查询通道列表
     * 
     * @param xdataPipe 通道
     * @return 通道集合
     */
    public List<XdataPipe> selectXdataPipeList(XdataPipe xdataPipe);

    /**
     * 新增通道
     * 
     * @param xdataPipe 通道
     * @return 结果
     */
    public int insertXdataPipe(XdataPipe xdataPipe);

    /**
     * 修改通道
     * 
     * @param xdataPipe 通道
     * @return 结果
     */
    public int updateXdataPipe(XdataPipe xdataPipe);

    /**
     * 删除通道
     * 
     * @param id 通道ID
     * @return 结果
     */
    public int deleteXdataPipeById(Long id);

    /**
     * 批量删除通道
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteXdataPipeByIds(Long[] ids);
}
