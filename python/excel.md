import openpyxl

workbook -> sheet -> cell


#   workbook:
    # 通过路径
    wb = openpyxl.load_workbook('test.xlsx')
    # 新建
    wb = Workbook()
    # 保存
    wb.save('path.xlsx')


#   sheet:
    # 通过名字
    sheet_names = wb.get_sheet_names()
    ws = wb.get_sheet_by_name(sheet_names[index])# index为0为第一张表 
    ws = wb['name']
    # 调用得到正在运行的工作表
    ws = wb.active
    ws = wb.get_active_sheet()
    # 新建
    ws1 = wb.create_sheet()  #默认插在最后
    ws2 = wb.create_sheet(0) #插在开头 
    ws3 = wb.create_sheet(title="Pi") #无title时，系统自动命名，依次为Sheet, Sheet1, Sheet2 ...
    # 删除
    wb.remove_sheet(ws)
    # 属性
    ws.title
    ws.max_row
    ws.max_column


#   cell:
    # 当一个工作表被创建时，其中是不包含单元格。只有当单元格被获取时才被创建
    
    # 通过字符索引
    c = ws['A4']     #读取单元格，如果不存在将在A4新建一个
    # 通过行列索引
    c = ws.cell(row = 4, column = 1) #行号列号从1开始 == ws.cell(4,1)
    # 属性
    c.row        # 所在行 4
    c.column     # 所在列 'A'
    c.coordinate # 所属坐标 'A2'
    c.value      # 对应的值 : NULL空值->None ; numberic->float ; string->unicode
  
    # 写入单元格（cell）值
    c.value
    ws.cell(row = 4, column = 2).value = 'test'
    ws.cell(row = 4, column = 2, value = 'test')
    
    ws['A4'] = 4 
    ws['C10'] = '=SUM(C1:C9)'  # 公式


    # 访问多个单元格
	cell_range = ws['A1':'C2']    # 使用切片获取多个单元格
	ws.get_cell_collection()     #读所有单元格数据
    
    # 按行、按列操作
    



#	样式
    # 字体
    from openpyxl.styles import Font, colors, Alignment
    
    italic24_Font = Font(size=24, italic=True)
    c.font = italic24_Font
    
    bold_itatic_24_font = Font(name="等线", size=24, italic=True, color=colors.RED, bold=True)
    sheet["B1"].font = bold_itatic_24_font
    
    # 对齐方式: B1 中的数据垂直居中和水平居中
    sheet["C1"].alignment = Alignment(horizontal="center", vertical="center")

    # 设置行高和列宽
    sheet.row_dimensions[1].height = 70
    sheet.column_dimensions['B'].width = 20
    
    # 合并单元格
    sheet.merge_cells('A1:D3')  #如果这些要合并的单元格都有数据，只会保留左上角的数据，其他则丢弃
    sheet.unmerge_cells('A1:D3')
    
    # 锁定行或列
    sheet.freeze_panes = 'A2'   #'A2'冻结第一行,'B1'冻结第一列；
    
    
    






