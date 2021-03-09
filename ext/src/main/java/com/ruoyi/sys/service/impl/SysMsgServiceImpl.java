package com.ruoyi.sys.service.impl;

import java.util.List;

import javax.naming.directory.SearchControls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.sys.mapper.SysMsgMapper;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.sys.domain.SysMsg;
import com.ruoyi.sys.service.ISysMsgService;
import com.ruoyi.system.mapper.SysUserMapper;

/**
 * 消息Service业务层处理
 * 
 * @author yepanpan
 * @date 2021-02-25
 */
@Service
public class SysMsgServiceImpl implements ISysMsgService 
{
    @Autowired
    private SysMsgMapper sysMsgMapper;
    @Autowired
    private SysUserMapper userMapper;

    /**
     * 查询消息
     * 
     * @param id 消息ID
     * @return 消息
     */
    @Override
    public SysMsg selectSysMsgById(Long id)
    {
    	SysMsg msg = sysMsgMapper.selectSysMsgById(id);
    	if(msg.getReadTime() == null && msg.getToUser().equals(SecurityUtils.getLoginUser().getUser().getUserId())) {
    		msg.setReadTime(DateUtils.getNowDate());
    		sysMsgMapper.updateSysMsg(msg);
    	}
    	return msg;
    }

    /**
     * 查询消息列表
     * 
     * @param sysMsg 消息
     * @return 消息
     */
    @Override
    public List<SysMsg> selectSysMsgList(SysMsg sysMsg)
    {
        return sysMsgMapper.selectSysMsgList(sysMsg);
    }

    /**
     * 新增消息
     * 
     * @param sysMsg 消息
     * @return 结果
     */
    @Override
    @Transactional
    public int insertSysMsg(SysMsg sysMsg)
    {
    	sysMsg.setAddUser(SecurityUtils.getLoginUser().getUser().getUserId());
    	sysMsg.setAddUserName(SecurityUtils.getLoginUser().getUser().getNickName());
    	sysMsg.setAddTime(DateUtils.getNowDate());
    	int count = 0;
    	if("2".equals(sysMsg.getType())){
    		SysUser bc = new SysUser();
    		bc.setStatus("0");
    		List<SysUser> list = userMapper.selectUserList(bc);
    		for(SysUser bt:list) {
    			SysMsg m = new SysMsg();
    			BeanUtils.copyProperties(sysMsg, m);
    			m.setToUser(bt.getUserId());
    			m.setToUserName(bt.getNickName());
    			sysMsgMapper.insertSysMsg(m);
    			count ++;
    		}
    	}else {
    		if(sysMsg.getUsers().length < 1) {
    			throw new CustomException("必须选择收件人");
    		}
    		for(Long userId:sysMsg.getUsers()) {
    			SysMsg m = new SysMsg();
    			BeanUtils.copyProperties(sysMsg, m);
    			SysUser u = userMapper.selectUserById(userId);
    			m.setToUser(userId);
    			m.setToUserName(u.getNickName());
    			sysMsgMapper.insertSysMsg(m);
    			count ++;
    		}
    	}
        return count;
    }

    /**
     * 修改消息
     * 
     * @param sysMsg 消息
     * @return 结果
     */
    @Override
    public int updateSysMsg(SysMsg sysMsg)
    {
        return sysMsgMapper.updateSysMsg(sysMsg);
    }

    /**
     * 批量删除消息
     * 
     * @param ids 需要删除的消息ID
     * @return 结果
     */
    @Override
    public int deleteSysMsgByIds(Long[] ids)
    {
        return sysMsgMapper.deleteSysMsgByIds(ids);
    }

    /**
     * 删除消息信息
     * 
     * @param id 消息ID
     * @return 结果
     */
    @Override
    public int deleteSysMsgById(Long id)
    {
        return sysMsgMapper.deleteSysMsgById(id);
    }
    

    /**
     * 批量标记消息已读
     * 
     * @param ids 需要标记的消息ID
     * @return 结果
     */
    @Override
    public int readMsgByIds(Long[] ids) {
    	return sysMsgMapper.readMsgByIds(ids);
    }
    

    /**
     * 查询指定用户的未读消息数
     * 
     * @param userId 用户ID
     * @return 结果
     */
    @Override
    public int countNoRead(Long userId) {
    	return sysMsgMapper.countNoRead(userId);
    }
    

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
    @Override
    @Transactional
    public int sendMsg(String title, String type, String content, long users[], String url) {
    	int count = 0;
    	
    	SysMsg sysMsg = new SysMsg();
    	sysMsg.setTitle(title);
    	sysMsg.setContent(content);
    	sysMsg.setType(type);
    	sysMsg.setUrl(url);
    	sysMsg.setAddTime(DateUtils.getNowDate());
    	
    	if("1".equalsIgnoreCase(type)) {
        	sysMsg.setAddUser(SecurityUtils.getLoginUser().getUser().getUserId());
        	sysMsg.setAddUserName(SecurityUtils.getLoginUser().getUser().getNickName());
    		for(long userId:users) {
    			sysMsg.setId(null);

    			SysUser u = userMapper.selectUserById(userId);
    			sysMsg.setToUser(userId);
    			sysMsg.setToUserName(u.getNickName());

    			sysMsgMapper.insertSysMsg(sysMsg);
    			count ++;
    		}
    	}else if("2".equalsIgnoreCase(type)) {
    		sysMsg.setAddUser(0l);
        	sysMsg.setAddUserName("系统");
    		for(long userId:users) {
    			sysMsg.setId(null);

    			SysUser u = userMapper.selectUserById(userId);
    			sysMsg.setToUser(userId);
    			sysMsg.setToUserName(u.getNickName());

    			sysMsgMapper.insertSysMsg(sysMsg);
    			count ++;
    		}
    	}else {
    		sysMsg.setAddUser(0l);
        	sysMsg.setAddUserName("系统");
        	if(users == null || users.length == 0) {
        		SysUser bc = new SysUser();
        		bc.setStatus("0");
        		List<SysUser> list = userMapper.selectUserList(bc);
        		for(SysUser bt:list) {
        			
        			sysMsg.setId(null);
        			sysMsg.setToUser(bt.getUserId());
        			sysMsg.setToUserName(bt.getNickName());
        			sysMsgMapper.insertSysMsg(sysMsg);
        			count ++;
        		}
        	}else {
        		for(long userId:users) {
        			sysMsg.setId(null);

        			SysUser u = userMapper.selectUserById(userId);
        			sysMsg.setToUser(userId);
        			sysMsg.setToUserName(u.getNickName());

        			sysMsgMapper.insertSysMsg(sysMsg);
        			count ++;
        		}
        	}
    		
    	}
    	return count;
    }
}
