import request from '@/utils/request'

// 查询资产维护任务列表
export function listMaintainTask(query) {
  return request({
    url: '/asset/maintain/task',
    method: 'get',
    params: query
  })
}

// 查询资产维护列表
export function listMyMaintain(query) {
  return request({
    url: '/asset/maintain/my',
    method: 'get',
    params: query
  })
}

// 查询资产维护列表
export function listMaintain(query) {
  return request({
    url: '/asset/maintain/list',
    method: 'get',
    params: query
  })
}

// 查询资产维护详细
export function getMaintain(id) {
  return request({
    url: '/asset/maintain/' + id,
    method: 'get'
  })
}

// 新增资产维护
export function addMaintain(data) {
  return request({
    url: '/asset/maintain',
    method: 'post',
    data: data
  })
}

// 修改资产维护
export function updateMaintain(data) {
  return request({
    url: '/asset/maintain',
    method: 'put',
    data: data
  })
}

// 删除资产维护
export function delMaintain(id) {
  return request({
    url: '/asset/maintain/' + id,
    method: 'delete'
  })
}

// 导出资产维护
export function exportMaintain(query) {
  return request({
    url: '/asset/maintain/export',
    method: 'get',
    params: query
  })
}

// 审核资产维护
export function checkMaintain(data) {
  return request({
    url: '/asset/maintain/check',
    method: 'put',
    data: data
  })
}
