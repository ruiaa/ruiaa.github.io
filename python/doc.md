import docx

# 无法准确定位，应当对每个段落和报告遍历输出，进行顺序定位

# 获取文档
file = docx.Document(path)
new_file = docx.Document()
new_file.save(path)

# 段落
file.paragraphs  
new_file.add_paragraph(para_data)


# 输出每一段的内容
for para in file.paragraphs:
    print(para.text)


    
for para in file.paragraphs:
    i = i + 1
    if i == 2:
        para.text = ' 编号： DB自 0000-00    制表：汪娇娇  制表日期：0000.00.00 共1页/第1页'

for table in file.tables:
    i = i + 1
    for row in table.rows:
        i = i + 1
        for cell in row.cells:
            i = i + 1
            print(i)
            print(cell.text)

            
#输出段落编号及段落内容
para_data = []
for i in range(len(file.paragraphs)):
    # for j in map(lambda x:x.split(' '),file.paragraphs[i].text):
    para_single = file.paragraphs[i].text.split(' ')
    while '' in para_single:  # 移除空格
        para_single.remove('')
    # para_data.append(para_single)
    for data_number in range(len(para_single)):
        data_num = re.findall(r"\d", para_single[data_number])
        data_num = ''.join(data_num)
        para_data.append(data_num + '    ')