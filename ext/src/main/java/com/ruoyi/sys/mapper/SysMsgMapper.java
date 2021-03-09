package com.ruoyi.sys.mapper;

import java.util.List;
import com.ruoyi.sys.domain.SysMsg;

/**
 * 消息Mapper接口
 * 
 * @author yepanpan
 * @date 2021-02-25
 */
public interface SysMsgMapper 
{
    /**
     * 查询消息
     * 
     * @param id 消息ID
     * @return 消息
     */
    public SysMsg selectSysMsgById(Long id);

    /**
     * 查询消息列表
     * 
     * @param sysMsg 消息
     * @return 消息集合
     */
    public List<SysMsg> selectSysMsgList(SysMsg sysMsg);

    /**
     * 新增消息
     * 
     * @param sysMsg 消息
     * @return 结果
     */
    public int insertSysMsg(SysMsg sysMsg);

    /**
     * 修改消息
     * 
     * @param sysMsg 消息
     * @return 结果
     */
    public int updateSysMsg(SysMsg sysMsg);

    /**
     * 删除消息
     * 
     * @param id 消息ID
     * @return 结果
     */
    public int deleteSysMsgById(Long id);

    /**
     * 批量删除消息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteSysMsgByIds(Long[] ids);
    


    /**
     * 批量标记消息已读
     * 
     * @param ids 需要标记的消息ID
     * @return 结果
     */
    public int readMsgByIds(Long[] ids);

    /**
     * 查询指定用户的未读消息数
     * 
     * @param userId 用户ID
     * @return 结果
     */
    public int countNoRead(Long userId);
}
