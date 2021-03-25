import request from '@/utils/request'

// 查询云盘共享列表
export function listShare(query) {
  return request({
    url: '/yun/share/list',
    method: 'get',
    params: query
  })
}

// 查询云盘共享详细
export function getShare(id) {
  return request({
    url: '/yun/share/' + id,
    method: 'get'
  })
}

// 新增云盘共享
export function addShare(data) {
  return request({
    url: '/yun/share',
    method: 'post',
    data: data
  })
}

// 修改云盘共享
export function updateShare(data) {
  return request({
    url: '/yun/share',
    method: 'put',
    data: data
  })
}

// 删除云盘共享
export function delShare(id) {
  return request({
    url: '/yun/share/' + id,
    method: 'delete'
  })
}

// 导出云盘共享
export function exportShare(query) {
  return request({
    url: '/yun/share/export',
    method: 'get',
    params: query
  })
}