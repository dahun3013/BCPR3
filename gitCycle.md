git pull upstream main   
git checkout -b develop   
git add -A   
git commit -m '업데이트 내용'   
git push origin develop   
git checkout main   
git merge develop   
git push origin main   

택 1 <br>
1.github GUI PR 사용 또는 <br>
2.git push upstream main <br>

git push origin --delete develop   
git branch -d develop
