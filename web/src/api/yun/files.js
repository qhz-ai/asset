import request from '@/utils/request'

// 查询云盘文件列表
export function listMyFiles(query) {
  return request({
    url: '/yun/files/my',
    method: 'get',
    params: query
  })
}

// 查询云盘文件列表
export function listShareFiles(query) {
  return request({
    url: '/yun/files/shares',
    method: 'get',
    params: query
  })
}

// 共享云盘文件
export function shareFiles(id) {
  return request({
    url: '/yun/files/share/'+id,
    method: 'get'
  })
}

// 查询云盘文件列表
export function listFiles(query) {
  return request({
    url: '/yun/files/list',
    method: 'get',
    params: query
  })
}

// 查询云盘文件详细
export function getFiles(id) {
  return request({
    url: '/yun/files/' + id,
    method: 'get'
  })
}

// 新增云盘文件
export function addFiles(data) {
  return request({
    url: '/yun/files',
    method: 'post',
    data: data
  })
}

// 修改云盘文件
export function updateFiles(data) {
  return request({
    url: '/yun/files',
    method: 'put',
    data: data
  })
}

// 删除云盘文件
export function delFiles(id) {
  return request({
    url: '/yun/files/' + id,
    method: 'delete'
  })
}

// 导出云盘文件
export function exportFiles(query) {
  return request({
    url: '/yun/files/export',
    method: 'get',
    params: query
  })
}
