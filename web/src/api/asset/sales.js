import request from '@/utils/request'

// 查询资产租售列表
export function listSales(query) {
  return request({
    url: '/asset/sales/list',
    method: 'get',
    params: query
  })
}

// 查询资产租售详细
export function getSales(id) {
  return request({
    url: '/asset/sales/' + id,
    method: 'get'
  })
}

// 新增资产租售
export function addSales(data) {
  return request({
    url: '/asset/sales',
    method: 'post',
    data: data
  })
}

// 修改资产租售
export function updateSales(data) {
  return request({
    url: '/asset/sales',
    method: 'put',
    data: data
  })
}

// 删除资产租售
export function delSales(id) {
  return request({
    url: '/asset/sales/' + id,
    method: 'delete'
  })
}

// 导出资产租售
export function exportSales(query) {
  return request({
    url: '/asset/sales/export',
    method: 'get',
    params: query
  })
}