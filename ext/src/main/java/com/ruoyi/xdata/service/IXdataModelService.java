package com.ruoyi.xdata.service;

import java.util.List;
import com.ruoyi.xdata.domain.XdataModel;

/**
 * 模型Service接口
 * 
 * @author yepanpan
 * @date 2020-12-29
 */
public interface IXdataModelService 
{
    /**
     * 查询模型
     * 
     * @param id 模型ID
     * @return 模型
     */
    public XdataModel selectXdataModelById(Long id);

    /**
     * 查询模型列表
     * 
     * @param xdataModel 模型
     * @return 模型集合
     */
    public List<XdataModel> selectXdataModelList(XdataModel xdataModel);

    /**
     * 新增模型
     * 
     * @param xdataModel 模型
     * @return 结果
     */
    public int insertXdataModel(XdataModel xdataModel);

    /**
     * 修改模型
     * 
     * @param xdataModel 模型
     * @return 结果
     */
    public int updateXdataModel(XdataModel xdataModel);

    /**
     * 批量删除模型
     * 
     * @param ids 需要删除的模型ID
     * @return 结果
     */
    public int deleteXdataModelByIds(Long[] ids);

    /**
     * 删除模型信息
     * 
     * @param id 模型ID
     * @return 结果
     */
    public int deleteXdataModelById(Long id);
}
