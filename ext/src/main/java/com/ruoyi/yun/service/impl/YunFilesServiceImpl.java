package com.ruoyi.yun.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.yun.mapper.YunFilesMapper;
import com.ruoyi.yun.mapper.YunFolderMapper;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.util.FileUtil;
import com.ruoyi.yun.domain.YunFiles;
import com.ruoyi.yun.domain.YunFolder;
import com.ruoyi.yun.service.IYunFilesService;

/**
 * 云盘文件Service业务层处理
 * 
 * @author yepanpan
 * @date 2021-03-15
 */
@Service
public class YunFilesServiceImpl implements IYunFilesService 
{
    @Autowired
    private YunFilesMapper yunFilesMapper;
    @Autowired
    private YunFolderMapper yunFolderMapper;

    /**
     * 查询共享云盘文件列表
     * 
     * @param yunFiles 云盘文件
     * @return 云盘文件集合
     */
    public List<YunFiles> selectShareFilesList(YunFiles yunFiles){
    	return yunFilesMapper.selectShareFilesList(yunFiles);
    }
    
    /**
     * 查询云盘文件
     * 
     * @param id 云盘文件ID
     * @return 云盘文件
     */
    @Override
    public YunFiles selectYunFilesById(Long id)
    {
        return yunFilesMapper.selectYunFilesById(id);
    }

    /**
     * 查询云盘文件列表
     * 
     * @param yunFiles 云盘文件
     * @return 云盘文件
     */
    @Override
    public List<YunFiles> selectYunFilesList(YunFiles yunFiles)
    {
        return yunFilesMapper.selectYunFilesList(yunFiles);
    }

    /**
     * 新增云盘文件
     * 
     * @param yunFiles 云盘文件
     * @return 结果
     */
    @Override
    public int insertYunFiles(YunFiles yunFiles)
    {
    	if(StringUtils.isEmpty(yunFiles.getType())) {
    		yunFiles.setType(FileUtil.getType(FileUtil.getExt(yunFiles.getTitle())));
    	}
    	yunFiles.setCreateTime(DateUtils.getNowDate());
    	yunFiles.setCreateBy(SecurityUtils.getUsername());
        return yunFilesMapper.insertYunFiles(yunFiles);
    }

    /**
     * 修改云盘文件
     * 
     * @param yunFiles 云盘文件
     * @return 结果
     */
    @Override
    public int updateYunFiles(YunFiles yunFiles)
    {
    	yunFiles.setUpdateTime(DateUtils.getNowDate());
    	yunFiles.setUpdateBy(SecurityUtils.getUsername());
        return yunFilesMapper.updateYunFiles(yunFiles);
    }

    /**
     * 批量删除云盘文件
     * 
     * @param ids 需要删除的云盘文件ID
     * @return 结果
     */
    @Override
    @Transactional
    public int deleteYunFilesByIds(String[] ids)
    {
    	int count = 0;
    	for(String id:ids) {
    		if(id.startsWith("folder_")) {
    			this.deleteYunFolder(Long.parseLong(id.substring(7)));;
    			count ++;        		
    		}else {
    			
    			if(id.startsWith("file_"))  id = id.substring(5);

    			yunFilesMapper.deleteYunFilesById(Long.parseLong(id));
    			count ++;        		
    		}
    	}
    	return count;
    }
    
    private void deleteYunFolder(Long id) {
    	YunFolder fc = new YunFolder();
    	fc.setPid(id);
    	List<YunFolder> list = yunFolderMapper.selectYunFolderList(fc);
    	for(YunFolder folder:list) {
    		this.deleteYunFolder(folder.getId());
    	}
		yunFolderMapper.deleteYunFolderById(id);
		yunFilesMapper.moveYunFiles(id, 0l);
    }

    /**
     * 删除云盘文件信息
     * 
     * @param id 云盘文件ID
     * @return 结果
     */
    @Override
    public int deleteYunFilesById(Long id)
    {
        return yunFilesMapper.deleteYunFilesById(id);
    }
    

    /**
     * 批量共享云盘文件
     * 
     * @param ids 需要共享的云盘文件或者目录
     * @return 结果
     */
    @Override
    @Transactional
    public int share(String[] ids) {
    	int count = 0;
    	for(String id:ids) {
    		if(id.startsWith("folder_")) {
    			YunFolder folder = yunFolderMapper.selectYunFolderById(Long.parseLong(id.substring(7)));
    			if(folder == null) continue;
    			
    			if("Y".equals(folder.getIsPublic())){
    				folder.setIsPublic("N");
    			}else {
    				folder.setIsPublic("Y");
    			}
    			yunFolderMapper.updateYunFolder(folder);
    			count ++;        		
    		}else {
    			
    			if(id.startsWith("file_"))  id = id.substring(5);
    			
    			YunFiles file = yunFilesMapper.selectYunFilesById(Long.parseLong(id));
    			if("Y".equals(file.getIsPublic())){
    				file.setIsPublic("N");
    			}else {
    				file.setIsPublic("Y");
    			}
    			yunFilesMapper.updateYunFiles(file);
    			count ++;        		
    		}
    	}
    	return count;
    }
}
