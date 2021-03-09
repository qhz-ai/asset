import request from '@/utils/request'

// 查询资产信息列表
export function listInfo(query) {
  return request({
    url: '/asset/info/list',
    method: 'get',
    params: query
  })
}

// 查询资产信息详细
export function getInfo(id) {
  return request({
    url: '/asset/info/' + id,
    method: 'get'
  })
}

// 新增资产信息
export function addInfo(data) {
  return request({
    url: '/asset/info',
    method: 'post',
    data: data
  })
}

// 修改资产信息
export function updateInfo(data) {
  return request({
    url: '/asset/info',
    method: 'put',
    data: data
  })
}

// 删除资产信息
export function delInfo(id) {
  return request({
    url: '/asset/info/' + id,
    method: 'delete'
  })
}

// 导出资产信息
export function exportInfo(query) {
  return request({
    url: '/asset/info/export',
    method: 'get',
    params: query
  })
}


// 下载导入模板
export function importTemplate() {
  return request({
    url: '/asset/info/importTemplate',
    method: 'get'
  })
}