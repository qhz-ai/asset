import request from '@/utils/request'

// 查询资产盘点列表
export function listStock(query) {
  return request({
    url: '/asset/stock/list',
    method: 'get',
    params: query
  })
}

// 查询资产盘点详细
export function getStock(id) {
  return request({
    url: '/asset/stock/' + id,
    method: 'get'
  })
}

// 新增资产盘点
export function addStock(data) {
  return request({
    url: '/asset/stock',
    method: 'post',
    data: data
  })
}

// 修改资产盘点
export function updateStock(data) {
  return request({
    url: '/asset/stock',
    method: 'put',
    data: data
  })
}

// 删除资产盘点
export function delStock(id) {
  return request({
    url: '/asset/stock/' + id,
    method: 'delete'
  })
}

// 导出资产盘点
export function exportStock(query) {
  return request({
    url: '/asset/stock/export',
    method: 'get',
    params: query
  })
}

// 计算盘点数据
export function countStock(id) {
  return request({
    url: '/asset/stock/count/' + id,
    method: 'get'
  })
}
