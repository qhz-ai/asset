import request from '@/utils/request'

// 查询应用列表
export function listClient(query) {
  return request({
    url: '/xdata/client/list',
    method: 'get',
    params: query
  })
}


// 查询应用列表
export function selectClient(query) {
  return request({
    url: '/xdata/client/select',
    method: 'get',
    params: query
  })
}

// 查询应用详细
export function getClient(id) {
  return request({
    url: '/xdata/client/' + id,
    method: 'get'
  })
}

// 新增应用
export function addClient(data) {
  return request({
    url: '/xdata/client',
    method: 'post',
    data: data
  })
}

// 修改应用
export function updateClient(data) {
  return request({
    url: '/xdata/client',
    method: 'put',
    data: data
  })
}

// 删除应用
export function delClient(id) {
  return request({
    url: '/xdata/client/' + id,
    method: 'delete'
  })
}

// 导出应用
export function exportClient(query) {
  return request({
    url: '/xdata/client/export',
    method: 'get',
    params: query
  })
}