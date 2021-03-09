import request from '@/utils/request'

// 查询通道列表
export function listPipe(query) {
  return request({
    url: '/xdata/pipe/list',
    method: 'get',
    params: query
  })
}

// 查询通道详细
export function getPipe(id) {
  return request({
    url: '/xdata/pipe/' + id,
    method: 'get'
  })
}

// 新增通道
export function addPipe(data) {
  return request({
    url: '/xdata/pipe',
    method: 'post',
    data: data
  })
}

// 修改通道
export function updatePipe(data) {
  return request({
    url: '/xdata/pipe',
    method: 'put',
    data: data
  })
}

// 删除通道
export function delPipe(id) {
  return request({
    url: '/xdata/pipe/' + id,
    method: 'delete'
  })
}

// 执行通道
export function runPipe(id) {
  return request({
    url: '/xdata/pipe/run/' + id,
    method: 'get'
  })
}

// 导出通道
export function exportPipe(query) {
  return request({
    url: '/xdata/pipe/export',
    method: 'get',
    params: query
  })
}
