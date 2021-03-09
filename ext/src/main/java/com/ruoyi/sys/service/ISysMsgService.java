package com.ruoyi.sys.service;

import java.util.List;
import com.ruoyi.sys.domain.SysMsg;

/**
 * 消息Service接口
 * 
 * @author yepanpan
 * @date 2021-02-25
 */
public interface ISysMsgService 
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
     * 
     * 发送消息
     * @param title 标题
     * @param type 类型：1=私人消息，2=系统消息，3=广播消息
     * @param content 消息内容
     * @param users 接收用户
     * @param url 打开链接
     * @return 结果
     */
    public int sendMsg(String title, String type, String content, long users[], String url);
    
    /**
     * 修改消息
     * 
     * @param sysMsg 消息
     * @return 结果
     */
    public int updateSysMsg(SysMsg sysMsg);

    /**
     * 批量删除消息
     * 
     * @param ids 需要删除的消息ID
     * @return 结果
     */
    public int deleteSysMsgByIds(Long[] ids);

    /**
     * 删除消息信息
     * 
     * @param id 消息ID
     * @return 结果
     */
    public int deleteSysMsgById(Long id);
    

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
