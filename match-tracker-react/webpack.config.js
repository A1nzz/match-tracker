const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  mode: 'development',
  entry: './src/index.js', // Точка входа
  output: {
    path: path.resolve(__dirname, 'dist'), // Папка для сборки
    filename: 'bundle.js', // Имя выходного файла
    publicPath: '/',
    clean: true, // Очищать папку dist перед каждой сборкой
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/, // Обрабатываем .js и .jsx файлы
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader',
        },
      },
      {
        test: /\.css$/,
        use: ['style-loader', 'css-loader'],
      },
      {
        test: /\.scss$/, // Обрабатываем .scss файлы
        use: ['style-loader', 'css-loader', 'sass-loader'],
      },
      {
        test: /\.(png|jpe?g|gif|svg)$/i, // Обработка изображений
        type: 'asset/resource',
      },
      {
        test: /\.(woff|woff2|eot|ttf|otf)$/i, // Обработка шрифтов
        type: 'asset/resource',
      },
    ],
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: './public/index.html', // Шаблон HTML
    }),
  ],
  devServer: {
    static: {
      directory: path.join(__dirname, 'dist'), // Папка для статики
    },
    compress: true,
    historyApiFallback: true, // Перенаправляет все запросы на index.html
    port: 3000, // Порт для разработки
    open: true, // Автоматически открывать браузер
  },
  resolve: {
    extensions: ['.js', '.jsx'], // Разрешения для импортов
  },
};