import request from '@/utils/request'

// 查询耗材信息列表
export function listMaterial(query) {
  return request({
    url: '/asset/material/list',
    method: 'get',
    params: query
  })
}

// 查询耗材信息详细
export function getMaterial(id) {
  return request({
    url: '/asset/material/' + id,
    method: 'get'
  })
}

// 新增耗材信息
export function addMaterial(data) {
  return request({
    url: '/asset/material',
    method: 'post',
    data: data
  })
}

// 修改耗材信息
export function updateMaterial(data) {
  return request({
    url: '/asset/material',
    method: 'put',
    data: data
  })
}

// 删除耗材信息
export function delMaterial(id) {
  return request({
    url: '/asset/material/' + id,
    method: 'delete'
  })
}

// 导出耗材信息
export function exportMaterial(query) {
  return request({
    url: '/asset/material/export',
    method: 'get',
    params: query
  })
}


// 下载导入模板
export function importTemplate() {
  return request({
    url: '/asset/material/importTemplate',
    method: 'get'
  })
}