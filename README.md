# Bomberman
***
## Mô tả
Game Bomberman viết bằng ngôn ngữ Java
***
## Thông tin nhóm
- **Nhóm 99**
- **Thành viên:**
  - Phạm Hồng Minh
  - Trịnh Huy Hoàng
## Hướng dẫn chơi
- Sử dụng `các phím điều hướng` hoặc `A`, `W`, `S`, `D` để di chuyển bomber, `SPACE` để đặt bom, `ESC` hoặc `P` để pause, `ENTER` để chọn
- Tiêu diệt tất cả quái và đi vào cổng để qua màn mới
- Bomber mất một mạng khi dính bom hoặc va chạm với quái
- Khi Bomber mất hết mạng trò chơi sẽ kết thúc
## Các chức năng
- Game gồm 8 màn chơi với độ khó tăng dần, mỗi màn sẽ xuất hiện một loại quái mới và sẽ có 1 item
  - Enemy
    - ![](/src/resouces/sprites/balloom_left1.png) `Balloom`: 
    - ![](/src/resouces/sprites/oneal_left1.png) `Oneal`: 
    - ![](/src/resouces/sprites/kondoria_left1.png) `Kondoria`: 
    - ![](/src/resouces/sprites/pontan1.png) ![](/src/resouces/sprites/pontan2.png) `Pontan`: 
    - ![](/src/resouces/sprites/minvo_left1.png) `Minvo`: 
    - ![](/src/resouces/sprites/toxic_left1.png) `Toxic`: 
    - ![](/src/resouces/sprites/doll_left1.png) `Doll`: 
    - ![](/src/resouces/sprites/brick_left1.png) `Brick`: 
  - Item
    - ![](/src/resouces/sprites/powerup_bombs.png) `Bomb Item`: 
    - ![](/src/resouces/sprites/powerup_flames.png) `Flame Item`: 
    - ![](/src/resouces/sprites/powerup_speed.png) `Speed Item`: 
- Game có thể lưu lại màn chơi khi tắt
- Chúc năng Ranking lưu lại tên của 5 người chơi có điểm cao nhất
