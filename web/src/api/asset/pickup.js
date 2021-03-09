import request from '@/utils/request'

// 查询耗材领用列表
export function listPickup(query) {
  return request({
    url: '/asset/pickup/list',
    method: 'get',
    params: query
  })
}

// 查询耗材领用详细
export function getPickup(id) {
  return request({
    url: '/asset/pickup/' + id,
    method: 'get'
  })
}

// 新增耗材领用
export function addPickup(data) {
  return request({
    url: '/asset/pickup',
    method: 'post',
    data: data
  })
}

// 修改耗材领用
export function updatePickup(data) {
  return request({
    url: '/asset/pickup',
    method: 'put',
    data: data
  })
}

// 删除耗材领用
export function delPickup(id) {
  return request({
    url: '/asset/pickup/' + id,
    method: 'delete'
  })
}

// 导出耗材领用
export function exportPickup(query) {
  return request({
    url: '/asset/pickup/export',
    method: 'get',
    params: query
  })
}

// 审核耗材领用
export function checkPickup(data) {
  return request({
    url: '/asset/pickup/check',
    method: 'put',
    data: data
  })
}
