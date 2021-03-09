import request from '@/utils/request'

// 查询入库管理列表
export function listEntering(query) {
  return request({
    url: '/asset/entering/list',
    method: 'get',
    params: query
  })
}

// 查询入库管理详细
export function getEntering(id) {
  return request({
    url: '/asset/entering/' + id,
    method: 'get'
  })
}

// 新增入库管理
export function addEntering(data) {
  return request({
    url: '/asset/entering',
    method: 'post',
    data: data
  })
}

// 修改入库管理
export function updateEntering(data) {
  return request({
    url: '/asset/entering',
    method: 'put',
    data: data
  })
}

// 删除入库管理
export function delEntering(id) {
  return request({
    url: '/asset/entering/' + id,
    method: 'delete'
  })
}

// 导出入库管理
export function exportEntering(query) {
  return request({
    url: '/asset/entering/export',
    method: 'get',
    params: query
  })
}