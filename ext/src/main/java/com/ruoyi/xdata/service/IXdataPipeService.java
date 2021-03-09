package com.ruoyi.xdata.service;

import java.util.List;
import com.ruoyi.xdata.domain.XdataPipe;

/**
 * 通道Service接口
 * 
 * @author yepanpan
 * @date 2020-12-29
 */
public interface IXdataPipeService 
{
    /**
     * 查询通道
     * 
     * @param id 通道ID
     * @return 通道
     */
    public XdataPipe selectXdataPipeById(Long id);
    
    /**
     * 执行通道
     * 
     * @param id 通道ID
     * @return 通道
     */
    public int run(Long id);

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
     * 批量删除通道
     * 
     * @param ids 需要删除的通道ID
     * @return 结果
     */
    public int deleteXdataPipeByIds(Long[] ids);

    /**
     * 删除通道信息
     * 
     * @param id 通道ID
     * @return 结果
     */
    public int deleteXdataPipeById(Long id);
}
