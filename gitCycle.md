git pull upstream main   
git checkout -b develop   
git add -A   
git commit -m '업데이트 내용'   
git push origin develop   
git checkout main   
git merge develop   
git push origin main   

github 사이트에서 PR 요청   

git push origin --delete develop   
git branch -d develop
