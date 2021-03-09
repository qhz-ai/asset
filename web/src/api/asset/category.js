import request from '@/utils/request'

// 查询资产分类列表
export function listCategory(query) {
  return request({
    url: '/asset/category/list',
    method: 'get',
    params: query
  })
}

// 查询资产分类详细
export function getCategory(id) {
  return request({
    url: '/asset/category/' + id,
    method: 'get'
  })
}

// 新增资产分类
export function addCategory(data) {
  return request({
    url: '/asset/category',
    method: 'post',
    data: data
  })
}

// 修改资产分类
export function updateCategory(data) {
  return request({
    url: '/asset/category',
    method: 'put',
    data: data
  })
}

// 删除资产分类
export function delCategory(id) {
  return request({
    url: '/asset/category/' + id,
    method: 'delete'
  })
}

// 导出资产分类
export function exportCategory(query) {
  return request({
    url: '/asset/category/export',
    method: 'get',
    params: query
  })
}