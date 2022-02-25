git pull upstream main   
git checkout -b develop   
git add -A   
git commit -m '업데이트 내용'   
git push origin develop   
git checkout main   
git merge develop   
git push origin main   

git push upstream main

git push origin --delete develop   
git branch -d develop
