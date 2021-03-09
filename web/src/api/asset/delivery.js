import request from '@/utils/request'

// 查询出库管理列表
export function listDelivery(query) {
  return request({
    url: '/asset/delivery/list',
    method: 'get',
    params: query
  })
}

// 查询出库管理详细
export function getDelivery(id) {
  return request({
    url: '/asset/delivery/' + id,
    method: 'get'
  })
}

// 新增出库管理
export function addDelivery(data) {
  return request({
    url: '/asset/delivery',
    method: 'post',
    data: data
  })
}

// 修改出库管理
export function updateDelivery(data) {
  return request({
    url: '/asset/delivery',
    method: 'put',
    data: data
  })
}

// 删除出库管理
export function delDelivery(id) {
  return request({
    url: '/asset/delivery/' + id,
    method: 'delete'
  })
}

// 导出出库管理
export function exportDelivery(query) {
  return request({
    url: '/asset/delivery/export',
    method: 'get',
    params: query
  })
}