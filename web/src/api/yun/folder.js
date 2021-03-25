import request from '@/utils/request'

// 查询云盘目录列表
export function listMyFolder(query) {
  return request({
    url: '/yun/folder/my',
    method: 'get',
    params: query
  })
}


// 查询云盘目录列表
export function listFolder(query) {
  return request({
    url: '/yun/folder/list',
    method: 'get',
    params: query
  })
}

// 查询云盘目录详细
export function getFolder(id) {
  return request({
    url: '/yun/folder/' + id,
    method: 'get'
  })
}

// 新增云盘目录
export function addFolder(data) {
  return request({
    url: '/yun/folder',
    method: 'post',
    data: data
  })
}

// 修改云盘目录
export function updateFolder(data) {
  return request({
    url: '/yun/folder',
    method: 'put',
    data: data
  })
}

// 删除云盘目录
export function delFolder(id) {
  return request({
    url: '/yun/folder/' + id,
    method: 'delete'
  })
}

// 导出云盘目录
export function exportFolder(query) {
  return request({
    url: '/yun/folder/export',
    method: 'get',
    params: query
  })
}