import request from '@/utils/request'

// 查询数据集列表
export function listDataset(query) {
  return request({
    url: '/sys/dataset/list',
    method: 'get',
    params: query
  })
}

// 查询数据
export function queryData(query) {
  return request({
    url: '/sys/dataset/query',
    method: 'post',
    data: query
  })
}


// 查询数据集详细
export function getDataset(id) {
  return request({
    url: '/sys/dataset/' + id,
    method: 'get'
  })
}

// 新增数据集
export function addDataset(data) {
  return request({
    url: '/sys/dataset',
    method: 'post',
    data: data
  })
}

// 修改数据集
export function updateDataset(data) {
  return request({
    url: '/sys/dataset',
    method: 'put',
    data: data
  })
}

// 删除数据集
export function delDataset(id) {
  return request({
    url: '/sys/dataset/' + id,
    method: 'delete'
  })
}

// 导出数据集
export function exportDataset(query) {
  return request({
    url: '/sys/dataset/export',
    method: 'get',
    params: query
  })
}