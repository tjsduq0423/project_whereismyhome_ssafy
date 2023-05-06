const data = [];
for (let i = 0; i < 64; i++) {
  data.push({
    isAnswered: false,
    title: `test${i}`,
    author: `author${i}`,
    createTime: `${i}시`,
  });
}

export default data;
