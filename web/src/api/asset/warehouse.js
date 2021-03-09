import request from '@/utils/request'

// 查询仓库列表
export function selectWarehouse(query) {
  return request({
    url: '/asset/warehouse/select',
    method: 'get',
    params: query
  })
}

// 查询仓库列表
export function listWarehouse(query) {
  return request({
    url: '/asset/warehouse/list',
    method: 'get',
    params: query
  })
}

// 查询仓库详细
export function getWarehouse(id) {
  return request({
    url: '/asset/warehouse/' + id,
    method: 'get'
  })
}

// 新增仓库
export function addWarehouse(data) {
  return request({
    url: '/asset/warehouse',
    method: 'post',
    data: data
  })
}

// 修改仓库
export function updateWarehouse(data) {
  return request({
    url: '/asset/warehouse',
    method: 'put',
    data: data
  })
}

// 删除仓库
export function delWarehouse(id) {
  return request({
    url: '/asset/warehouse/' + id,
    method: 'delete'
  })
}

// 导出仓库
export function exportWarehouse(query) {
  return request({
    url: '/asset/warehouse/export',
    method: 'get',
    params: query
  })
}