import request from '@/utils/request'

// 查询文件列表
export function listFiles(query) {
  return request({
    url: '/sys/files/list',
    method: 'get',
    params: query
  })
}

// 查询文件详细
export function getFiles(id) {
  return request({
    url: '/sys/files/' + id,
    method: 'get'
  })
}

// 新增文件
export function addFiles(data) {
  return request({
    url: '/sys/files',
    method: 'post',
    data: data
  })
}

// 修改文件
export function updateFiles(data) {
  return request({
    url: '/sys/files',
    method: 'put',
    data: data
  })
}

// 删除文件
export function delFiles(id) {
  return request({
    url: '/sys/files/' + id,
    method: 'delete'
  })
}

// 导出文件
export function exportFiles(query) {
  return request({
    url: '/sys/files/export',
    method: 'get',
    params: query
  })
}