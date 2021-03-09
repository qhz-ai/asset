import request from '@/utils/request'

// 查询模型列表
export function listModel(query) {
  return request({
    url: '/xdata/model/list',
    method: 'get',
    params: query
  })
}

// 查询模型列表
export function selectModel(query) {
  return request({
    url: '/xdata/model/select',
    method: 'get',
    params: query
  })
}

// 查询模型详细
export function getModel(id) {
  return request({
    url: '/xdata/model/' + id,
    method: 'get'
  })
}

// 新增模型
export function addModel(data) {
  return request({
    url: '/xdata/model',
    method: 'post',
    data: data
  })
}

// 修改模型
export function updateModel(data) {
  return request({
    url: '/xdata/model',
    method: 'put',
    data: data
  })
}

// 删除模型
export function delModel(id) {
  return request({
    url: '/xdata/model/' + id,
    method: 'delete'
  })
}

// 导出模型
export function exportModel(query) {
  return request({
    url: '/xdata/model/export',
    method: 'get',
    params: query
  })
}