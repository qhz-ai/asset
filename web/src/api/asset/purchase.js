import request from '@/utils/request'

// 查询个的人采购任务列表
export function listPurchaseTask(query) {
  return request({
    url: '/asset/purchase/task',
    method: 'get',
    params: query
  })
}

// 查询个的人采购申请列表
export function listMyPurchase(query) {
  return request({
    url: '/asset/purchase/my',
    method: 'get',
    params: query
  })
}

// 查询采购申请列表
export function listPurchase(query) {
  return request({
    url: '/asset/purchase/list',
    method: 'get',
    params: query
  })
}

// 查询采购申请详细
export function getPurchase(id) {
  return request({
    url: '/asset/purchase/' + id,
    method: 'get'
  })
}

// 新增采购申请
export function addPurchase(data) {
  return request({
    url: '/asset/purchase',
    method: 'post',
    data: data
  })
}

// 修改采购申请
export function updatePurchase(data) {
  return request({
    url: '/asset/purchase',
    method: 'put',
    data: data
  })
}

// 审核采购申请
export function checkPurchase(data) {
  return request({
    url: '/asset/purchase/check',
    method: 'put',
    data: data
  })
}

// 删除采购申请
export function delPurchase(id) {
  return request({
    url: '/asset/purchase/' + id,
    method: 'delete'
  })
}

// 导出采购申请
export function exportPurchase(query) {
  return request({
    url: '/asset/purchase/export',
    method: 'get',
    params: query
  })
}
