rm -r templates
rm -r static
rm -r ../backend/src/main/webapp/static/*
npm run build
cp templates/index.html ../backend/src/main/webapp/
cp -a static ../backend/src/main/webapp/