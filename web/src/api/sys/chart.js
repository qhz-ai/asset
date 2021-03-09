import request from '@/utils/request'

// 查询报表管理列表
export function listChart(query) {
  return request({
    url: '/sys/chart/list',
    method: 'get',
    params: query
  })
}

// 查询报表管理详细
export function getChart(id) {
  return request({
    url: '/sys/chart/' + id,
    method: 'get'
  })
}

// 查询报表管理详细
export function getChartByCode(code) {
  return request({
    url: '/sys/chart/code/' + code,
    method: 'get'
  })
}

// 新增报表管理
export function addChart(data) {
  return request({
    url: '/sys/chart',
    method: 'post',
    data: data
  })
}

// 修改报表管理
export function updateChart(data) {
  return request({
    url: '/sys/chart',
    method: 'put',
    data: data
  })
}

// 删除报表管理
export function delChart(id) {
  return request({
    url: '/sys/chart/' + id,
    method: 'delete'
  })
}

// 加入报表菜单
export function menuChart(id) {
  return request({
    url: '/sys/chart/menu/' + id,
    method: 'get'
  })
}


// 导出报表管理
export function exportChart(query) {
  return request({
    url: '/sys/chart/export',
    method: 'get',
    params: query
  })
}
