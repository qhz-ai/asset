import request from '@/utils/request'

// 查询资产盘点列表
export function listTaking(query) {
  return request({
    url: '/assets/taking/list',
    method: 'get',
    params: query
  })
}

// 查询资产盘点详细
export function getTaking(id) {
  return request({
    url: '/assets/taking/' + id,
    method: 'get'
  })
}

// 新增资产盘点
export function addTaking(data) {
  return request({
    url: '/assets/taking',
    method: 'post',
    data: data
  })
}

// 修改资产盘点
export function updateTaking(data) {
  return request({
    url: '/assets/taking',
    method: 'put',
    data: data
  })
}

// 删除资产盘点
export function delTaking(id) {
  return request({
    url: '/assets/taking/' + id,
    method: 'delete'
  })
}

// 导出资产盘点
export function exportTaking(query) {
  return request({
    url: '/assets/taking/export',
    method: 'get',
    params: query
  })
}